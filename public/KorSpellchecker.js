import * as ko from koSpellchecker

ko.check('외 않되?', function(err, result){
    if(err) {
        return console.error(err)
    } 
    console.log(result)
})