let all = "";

function add(num) {
    all += num;
    document.getElementById("display").value = all;
}

function sum() {
    document.getElementById("display").value = eval(all);
}

function clearDisplay() {
    all = "";
    document.getElementById("display").value = "0";
}

function tan(all) {
    all.value = Math.tan((all.value * 3.141592) / 180);
}

function sin(all) {
    all.value = Math.sin((all.value * 3.141592) / 180);
}

function cos(all) {
    all.value = Math.cos((all.value * 3.141592) / 180);
}

function abs(all) {
    all.value = Math.abs(all.value);
}

function pow(all) {
    all.value = Math.pow(all.value, 2);
}