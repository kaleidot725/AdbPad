# Warn when there is a big PR
warn("Big PR") if git.lines_of_code > 500

# Parse junit test report
`find . | grep "TEST-*"`.split("\n").each do |path|
    junit.parse(path)
    junit.report
end

# Enable ktlint
ktlint.lint
