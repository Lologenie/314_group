

# Specify the duration for which you want to collect performance data (in seconds)
$Duration = 10

# Specify the output CSV file path
$OutputFile = "C:\Users\Mykol\Desktop\PerformanceData.csv"

# Define the performance counters to collect
$Counters = @(
'\GPU Local Adapter Memory(*)\Local Usage',
'\GPU Engine(*)\Utilization Percentage',
'\GPU Engine(*)\Running Time',
'\GPU Adapter Memory(*)\Shared Usage',
'\GPU Adapter Memory(*)\Dedicated Usage',
'\GPU Adapter Memory(*)\Total Committed',
'\GPU Process Memory(*)\Shared Usage',
'\GPU Process Memory(*)\Dedicated Usage',
'\GPU Process Memory(*)\Non Local Usage',
'\GPU Process Memory(*)\Local Usage',
'\GPU Process Memory(*)\Total Committed'

)

# Start collecting performance data
try {
    $PerformanceData = Get-Counter -Counter $Counters -SampleInterval 1 -MaxSamples $Duration -ErrorAction Stop
    # Save the collected data to a CSV file
    $PerformanceData | Export-Csv -Path $OutputFile -NoTypeInformation
    Write-Host "Performance data collected and saved to: $OutputFile"
} catch {
    Write-Host "Error collecting performance data: $_"
}
