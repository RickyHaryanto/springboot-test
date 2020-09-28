if('serviceWorker' in navigator) {
    navigator.serviceWorker.register("/sw.js")
        .then((registration) => { 
           console.log(`service worker registered succesfully ${registration}`) 
        })
        .catch((err) => {
            console.log(`Error registring ${err}`) 
        })
} else {
console.log(`Service worker is not supported in this browser.`)
}