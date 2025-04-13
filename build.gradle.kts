plugins {
	java
}

tasks.register("run") {
	dependsOn(gradle.includedBuild("orderservice").task(":app:run"))
	dependsOn(gradle.includedBuild("paymentservice").task(":app:run"))
}
tasks.register("bootRun") {
	dependsOn(gradle.includedBuild("orderservice").task(":bootRun"))
	dependsOn(gradle.includedBuild("paymentservice").task(":bootRun"))
}