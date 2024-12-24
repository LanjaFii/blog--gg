window.addEventListener("load", function() {
    document.body.classList.remove("loading");
    document.getElementById("loading-screen").style.display = "none";
});

// Activer l'animation de chargement au chargement de la page
document.body.classList.add("loading");