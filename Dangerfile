# Warn when there is a big PR
warn("Big PR") if git.lines_of_code > 500

`find . | grep "TEST-*"`.split("\n").each do |path|
    junit.parse(path)
    junit.report
end