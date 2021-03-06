let matchid = new URLSearchParams(window.location.search).get('matchid')
let nameHeading = document.querySelector("#matchname")
document.querySelector("#organizerbutton").addEventListener('click', ev => {
    window.location.href = window.location.origin + "/admin.html?matchid=" + matchid
})

function fillTable(data) {
    let template = document.querySelector("#playertemplate")
    let m = new Array(data);
    for (let match of m) {
        for (let team of match) {
            let n = team['nummer']
            document.querySelector("#team" + n + "name").textContent = team['naam']
            for (let player of team['spelers']) {
                let clone = template.content.cloneNode(true)
                let name = clone.querySelector("#name")
                name.textContent = player['naam']
                    document.querySelector("#team" + n).appendChild(clone)

            }
        }
    }
}


fetch("/match/" + matchid)
    .then(result => {
        if (result.ok) {
            result.json()
                .then(data => {
                    nameHeading.textContent = data['matchname'];
                    fillTable(data['teams'])
                })
        } else {
            window.alert("Match not found!")
        }
    })
    .catch(err => {window.alert("error setting up match: " + err); console.log(err)})
