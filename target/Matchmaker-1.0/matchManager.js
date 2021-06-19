let matchid = new URLSearchParams(window.location.search).get('matchid')
let template = document.querySelector("#playertemplate")

function fillTable(data) {
    let template = document.querySelector("#playertemplate")
    let team1table = document.querySelector("#team1")
    let team2table = document.querySelector("#team2")

    console.log(data)
    let teams = new Array(data);
    console.log(teams)

    for (let team of teams){
        let n = team['nummer']
        console.log(n)
        for (let player of team){
            console.log("psa")
            let clone = template.content.cloneNode(true)
            let name = clone.querySelector("#name")
            name.textContent = player['name']
            if(n === 1){
                console.log('appended team 1')
                team1table.appendChild(clone)
            }
            if(n === 2){
                console.log('appended team 2')
                team2table.appendChild(clone)
            }
        }
    }
}


fetch("/match/" + matchid)
    .then(result => {if (result.ok){
        result.json()
            .then(data => {console.log(data); fillTable(data['teams'])})
    }
    else{
        window.alert("Match not found!")
    }
    })
