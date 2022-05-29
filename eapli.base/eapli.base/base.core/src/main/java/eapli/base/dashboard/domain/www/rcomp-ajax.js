function refreshTable() {
    var request = new XMLHttpRequest();
    var vBoard=document.getElementById("info");

    request.onload = function() {
        vBoard.innerHTML = this.responseText;
        vBoard.style.color="white";
        setTimeout(refreshTable, 2000);
    };

    request.ontimeout = function() {
        vBoard.innerHTML = "Server timeout, still trying ...";
        vBoard.style.color="red";
        setTimeout(refreshTable, 100);
    };

    request.onerror = function() {
        vBoard.innerHTML = "No server reply, still trying ...";
        vBoard.style.color="red";
        setTimeout(refreshTable, 5000);
    };

    request.open("GET", "/info", true);
    request.timeout = 5000;
    request.send();
}