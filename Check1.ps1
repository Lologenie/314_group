$runningServices = Get-Service | Where-Object { $_.Status -eq "Running" }
$stoppedServices = Get-Service | Where-Object { $_.Status -eq "Stopped" }

Write-Host "Кількість працюючих служб: $($runningServices.Count)"
Write-Host "Кількість зупинених служб: $($stoppedServices.Count)"
