let hours = 0;
let minutes = 0;
let seconds = 0;
const appendSeconds = document.getElementById("seconds");
const appendMinutes = document.getElementById("minutes");
const appendHours = document.getElementById("hours");
const buttonStart = document.getElementById("bt__start");
const buttonStop = document.getElementById("bt__stop");
const buttonReset = document.getElementById("bt__reset");
let intervalId;
 
buttonStart.onclick = function(){
  clearInterval(intervalId)
  intervalId = setInterval(operateTimer, 1000)
}
 
buttonStop.onclick = function(){
  var li = document.createElement('li');
  li.textContent= hours+':'+ minutes + ":" + seconds;
  if(! recordList.firstChild){
    recordList.append(li);
  }
  clearInterval(intervalId)
}
 
buttonReset.onclick = function(){
  clearInterval(intervalId)
  seconds = 0; minutes = 0; hours = 0;
  appendSeconds.textContent = "00"
  appendMinutes.textContent = "00"
  appendHours.textContent = "00"
}
 

function operateTimer(){
  seconds++;
  appendSeconds.textContent = seconds > 9 ? seconds : '0' + seconds
  if(seconds > 59){
    minutes++;
    appendMinutes.textContent = minutes > 9 ? minutes : '0' + minutes
    seconds = 0
    appendSeconds.textContent = "00"
  }
  if(minutes > 59){
    hours++;
    appendHours.textContent = hours > 9 ? hours : '0' + hours
    minutes = 0
    appendMinutes.textContent = "00"    
  }
}