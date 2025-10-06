$services = @("api-gateway", "customer-service", "eureka-server", "ms-customer", "ms-product")

foreach ($service in $services) {
    Write-Host "--- Buildando o microsservico: $service ---"

    Push-Location $service

    .\gradlew.bat clean bootJar

    Pop-Location

    Write-Host "--- Finalizando o build de $service ---`n"
}

Write-Host "Todos os servicos buildados com sucesso!"