package jp.kaleidot725.adbpad.ui.component.text

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun AutoSizableText(
    text: String,
    style: TextStyle,
    modifier: Modifier = Modifier,
    minFontSize: TextUnit = 1.sp,
    maxFontSize: TextUnit = 100.sp,
    granularityInPx: Int = 1,
) {
    val autoSizer = rememberAutoSizer(minFontSize, maxFontSize, granularityInPx)

    BoxWithConstraints(modifier = modifier) {
        val fontSize =
            remember(text, autoSizer, style, constraints) {
                autoSizer.autoSize(
                    text = text,
                    style = style,
                    constraints = constraints,
                    overflow = TextOverflow.Ellipsis,
                )
            }

        Text(
            text = text,
            fontSize = fontSize,
            overflow = TextOverflow.Ellipsis,
            style = style,
        )
    }
}

class AutoSizer(
    minFontSize: TextUnit,
    maxFontSize: TextUnit,
    granularityInPx: Int,
    density: Density,
    private val textMeasurer: TextMeasurer,
) {
    // 候補となるフォントサイズのリスト
    // 二分探索できるように順に並べる
    private val fontSizes: List<TextUnit> =
        generateSequence(minFontSize) { previous ->
            with(density) {
                (previous.toPx() + granularityInPx).toSp()
            }
        }.takeWhile { it < maxFontSize }
            .plus(maxFontSize)
            .toList()

    fun autoSize(
        text: String,
        style: TextStyle,
        constraints: Constraints,
        overflow: TextOverflow,
    ): TextUnit {
        // 二分探索で最適なフォントサイズを取得する
        val index =
            fontSizes.binarySearch { targetFontSize ->
                val result =
                    textMeasurer.measure(
                        text = text,
                        style = style.copy(fontSize = targetFontSize),
                        constraints = constraints,
                        overflow = overflow,
                    )

                // はみ出していれば正、そうでなければ負を返すと、
                // 「最適なフォントサイズの『inverted insertion point』」が得られる
                // see: https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/binary-search.html
                if (result.hasVisualOverflow) 1 else -1
            }

        val insertionPoint = -(index + 1)
        // 最適なフォントサイズの挿入位置は、はみ出すフォントサイズのうち最小のものの位置に等しいので、
        // その一つ前を返す
        val resultIndex = (insertionPoint - 1).coerceAtLeast(0)
        return fontSizes[resultIndex]
    }
}

@Composable
fun rememberAutoSizer(
    minFontSize: TextUnit,
    maxFontSize: TextUnit,
    granularityInPx: Int,
): AutoSizer {
    val density = LocalDensity.current
    val textMeasurer = rememberTextMeasurer()

    return remember(minFontSize, maxFontSize, granularityInPx, density, textMeasurer) {
        AutoSizer(minFontSize, maxFontSize, granularityInPx, density, textMeasurer)
    }
}
