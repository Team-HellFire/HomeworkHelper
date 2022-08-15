let hours=0;
let minutes=0;
let seconds=0;

const appendSeconds = document.getElementById("seconds");
const appendMinutes = document.getElementById("minutes");
const appendHours = document.getElementById("hours");

const plusOneHour = document.getElementById("oneHour");
const plusTenMin = document.getElementById("tenMin");
const plusOneMin = document.getElementById("oneMin");
const plusTensec = document.getElementById("tenSec");

const buttonStart = document.getElementById("start");
const buttonStop = document.getElementById("stop");
const buttonReset = document.getElementById("reset");


plusOneHour.onclick= function(){
    hours+=1;
    appendHours.textContent=hours > 9 ? hours : '0' + hours;
}
plusTenMin.onclick= function(){
    minutes+=10;
    if(minutes>59){
        hours++;
        appendHours.textContent = hours > 9 ? hours : '0' + hours;
        minutes -= 60;  
    }
    appendMinutes.textContent=minutes > 9 ? minutes : '0' + minutes;
}
plusOneMin.onclick= function(){
    minutes+=1;
    if(minutes>59){
        hours++;
        appendHours.textContent = hours > 9 ? hours : '0' + hours;
        minutes-=60;
    }
    appendMinutes.textContent=minutes > 9 ? minutes : '0' + minutes;
}
plusTensec.onclick=function(){
    seconds+=10;
    if(seconds>59){
        minutes++;
        appendMinutes.textContent = minutes > 9 ? minutes : '0' + minutes;
        seconds=0;
    }
    appendSeconds.textContent= seconds>9 ? seconds: '0'+seconds
}


