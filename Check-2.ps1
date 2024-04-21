
$serviceName = 'WerSvc'  # Ім'я служби Windows Error Reporting Service

$service = Get-Service -Name $serviceName

if ($service.Status -ne 'Running') {
    # Якщо служба не запущена, то спробуємо її запустити
    Write-Host "Служба '$serviceName' не запущена. Спроба запуску..."
    Start-Service -Name $serviceName
    Start-Sleep -Seconds 5  # Зачекайте кілька секунд на завершення запуску
    $service = Get-Service -Name $serviceName  # Оновимо інформацію про службу

    if ($service.Status -eq 'Running') {
        Write-Host "Служба '$serviceName' успішно запущена."
    } else {
        Write-Host "Не вдалося запустити службу '$serviceName'."
    }
} else {
    Write-Host "Служба '$serviceName' вже запущена."
}
