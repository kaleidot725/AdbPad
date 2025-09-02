# Warn when there is a big PR
warn("Big PR") if git.lines_of_code > 500

# Parse junit test report
`find . | grep "TEST-*"`.split("\n").each do |path|
    junit.parse(path)
    junit.report
end

# Notify ktlint warning
checkstyle_format.base_path = Dir.pwd
ktlint_report_path = 'build/reports/ktlint/ktlintJvmMainSourceSetCheck/ktlintJvmMainSourceSetCheck.xml'
if File.exist?(ktlint_report_path)
  checkstyle_format.report ktlint_report_path
else
  puts "KtLint checkstyle report not found at #{ktlint_report_path}. Skipping ktlint report."
end