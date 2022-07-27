var options = {
        "textarea": document.getElementById("textarea"),
        "submitBtn": document.getElementById("submitBtn"),
        "resultarea": document.getElementById("result"),
        "callback": function(data) {
            console.log(data);
        }
    };
    var spell = new spell(options);
    spell.init();