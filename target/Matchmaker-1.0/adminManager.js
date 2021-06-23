let matchid = new URLSearchParams(window.location.search).get('matchid')
let nameHeading = document.querySelector("#matchname")
let orgnamep = document.querySelector("#orgname-p")

document.querySelector("#returnbutton").addEventListener('click', ev => {
    window.location.href = window.location.origin + "/match.html?matchid=" + matchid
})

document.querySelector("#editbutton").addEventListener('click', ev => {
    ev.preventDefault()
    editMatch(new FormData(document.querySelector("#editform")))
})

document.querySelector("#loginbutton").addEventListener("click", e => {
    e.preventDefault()
    adminLogin(document.querySelector("#orgname-p").textContent, document.querySelector("#adminPass").value)
})

document.querySelector("#deleteform").addEventListener("submit", e => {
    e.preventDefault()
    deleteMatch()
})

fetch("/match/" + matchid)
    .then(result => {
        if (result.ok) {
            result.json()
                .then(data => {
                    nameHeading.textContent = data['matchname'] + " (organizer view)";
                    orgnamep.textContent = data['organisator']['naam']
                    document.querySelector("#matchnameinput").value = data['matchname']
                    fillTable(data['teams'])
                })
        } else {
            window.alert("Match not found!")
        }
    })
    .catch(err => {window.alert("error setting up match: " + err); console.log(err)})

