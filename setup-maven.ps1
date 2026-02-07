$mavenUrl = 'https://archive.apache.org/dist/maven/maven-3/3.9.12/binaries/apache-maven-3.9.12-bin.zip'
$mavenHome = 'C:\Users\L13Y12W21\AppData\Local\Maven'
$zipPath = Join-Path $mavenHome 'maven.zip'

# Create directory if not exists
New-Item -ItemType Directory -Path $mavenHome -Force | Out-Null

Write-Host "Downloading Maven from $mavenUrl..."
[System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor [System.Net.SecurityProtocolType]::Tls12

try {
    Invoke-WebRequest -Uri $mavenUrl -OutFile $zipPath -ErrorAction Stop
    Write-Host "Download complete!"
    
    # Extract zip
    Write-Host "Extracting Maven..."
    Add-Type -AssemblyName System.IO.Compression.FileSystem
    [System.IO.Compression.ZipFile]::ExtractToDirectory($zipPath, $mavenHome)
    Remove-Item $zipPath
    
    # Set environment variable
    $mavenBinPath = Join-Path $mavenHome 'apache-maven-3.9.12'
    [System.Environment]::SetEnvironmentVariable('MAVEN_HOME', $mavenBinPath, 'User')
    Write-Host "MAVEN_HOME environment variable set to $mavenBinPath"
    
    Write-Host 'Maven installation complete!'
}
catch {
    Write-Host "Error: $_"
}
