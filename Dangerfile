# Warn when there is a big PR
warn("Big PR") if git.lines_of_code > 500

# Parse junit test report
`find . | grep "TEST-*"`.split("\n").each do |path|
    junit.parse(path)
    junit.report
end

# Notify ktlint warning
checkstyle_format.base_path = Dir.pwd

# Find ktlint XML reports in all modules
ktlint_reports = Dir.glob('**/reports/ktlint/**/*.xml')
if ktlint_reports.empty?
  puts "No KtLint XML reports found. Skipping ktlint report."
else
  ktlint_reports.each do |report_path|
    puts "Processing ktlint report: #{report_path}"
    checkstyle_format.report report_path
  end
end