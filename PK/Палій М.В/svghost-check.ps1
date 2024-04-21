$svchostProcesses = Get-Process svchost
$cpuTotal = $svchostProcesses | Measure-Object CPU -Sum
$workingSetTotal = $svchostProcesses | Measure-Object PM -Sum
$virtualMemoryTotal = $svchostProcesses | Measure-Object VM -Sum

$svchostProcesses | Sort-Object CPU -Descending | Format-Table Name, CPU, PM, VM -AutoSize
Write-Host "Сумарні показники:"
Write-Host "Сумарний CPU: $($cpuTotal.Sum) s"
Write-Host "Сумарний PM: $($workingSetTotal.Sum) KB"
Write-Host "Сумарна VM: $($virtualMemoryTotal.Sum) KB"
