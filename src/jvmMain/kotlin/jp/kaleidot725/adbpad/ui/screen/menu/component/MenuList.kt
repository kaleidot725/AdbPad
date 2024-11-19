package jp.kaleidot725.adbpad.ui.screen.menu.component

// @Composable
// fun MenuList(
//    menus: List<Menu>,
//    selectedMenu: Menu?,
//    onSelectMenu: (Menu) -> Unit,
//    modifier: Modifier = Modifier,
// ) {
//    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
//        menus.forEach { menu ->
//            val isSelected = menu == selectedMenu
//            MenuItem(
//                icon = menu.icon,
//                iconDescription = "$menu Icon",
//                text = menu.title,
//                modifier =
//                    Modifier
//                        .fillMaxWidth()
//                        .clip(RoundedCornerShape(8.dp))
//                        .clickableBackground(isSelected = isSelected)
//                        .clickable { onSelectMenu(menu) }
//                        .padding(horizontal = 8.dp)
//                        .padding(vertical = 8.dp),
//            )
//        }
//    }
// }
//
// @Preview
// @Composable
// private fun MenuList_Preview() {
//    MenuList(
//        menus = listOf(Menu.Command, Menu.InputText, Menu.Screenshot),
//        selectedMenu = Menu.Command,
//        onSelectMenu = {},
//    )
// }
