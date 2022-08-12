# Warn when there is a big PR
warn("Big PR") if git.lines_of_code > 500

# Parse junit test report
`find . | grep "TEST-*"`.split("\n").each do |path|
    junit.parse(path)
    junit.report
end

# Notify ktlint warning
checkstyle_format.base_path = Dir.pwd
checkstyle_format.report 'build/reports/ktlint/ktlintMainSourceSetCheck/ktlintMainSourceSetCheck.xml'