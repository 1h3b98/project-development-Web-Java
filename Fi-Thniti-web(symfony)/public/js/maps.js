window.onload = function(){
    let macarte = L.map('carte').setView([36.76121 , 9.52198], 7)
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors',
        minZoom: 1,
        maxZoom: 20
    }).addTo(macarte)
    L.Routing.control({
        lineOptions: {
            styles: [{color: '#ff8f00', opacity: 1, weight: 7}]
        },
        router: new L.Routing.osrmv1({
            language: 'fr',
            profile: 'car', // car, bike, foot
        }),
        geocoder: L.Control.Geocoder.nominatim()
    }).addTo(macarte)
    
}
