param($version)

$imageName = "makar1031/tamagotchi-basic-mechanics"

.\mvnw clean release:prepare
docker build -t $imageName .
docker tag "$imageName`:latest" "$imageName`:$version"
docker push "$imageName`:latest"
docker push "$imageName`:$version"
.\mvnw clean release:clean
