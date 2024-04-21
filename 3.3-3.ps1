# Specify the path to the CSV file containing the collected performance data
$InputFile = "C:\Users\Kostia\Desktop\PerformanceData.csv"

# Specify the output report file path
$OutputReportFile = "C:\Users\Kostia\Desktop\PerformanceReport.txt"

# Load the performance data from the CSV file
$PerformanceData = Import-Csv -Path $InputFile

# Define a function to calculate the standard deviation
function Get-StandardDeviation {
    param (
        [double[]]$Values
    )
    
    $Mean = $Values | Measure-Object -Average | Select-Object -ExpandProperty Average
    $SumSquaredDifferences = $Values | ForEach-Object { ($_ - $Mean) * ($_ - $Mean) } | Measure-Object -Sum | Select-Object -ExpandProperty Sum
    $Variance = $SumSquaredDifferences / $Values.Count
    $StandardDeviation = [Math]::Sqrt($Variance)
    
    return $StandardDeviation
}

# Iterate through each performance counter and calculate standard deviation
$ReportContent = @()
foreach ($Counter in $PerformanceData.PSObject.Properties.Name) {
    echo $Counter
  
    $CounterValues = $PerformanceData.$Counter | ForEach-Object { [double]$_ }
    $StdDev = Get-StandardDeviation -Values $CounterValues
    
    $ReportContent += "$Counter Standard Deviation: $StdDev"
}

# Save the report to a .txt file
$ReportContent | Out-File -FilePath $OutputReportFile

Write-Host "Performance report generated and saved to: $OutputReportFile"
