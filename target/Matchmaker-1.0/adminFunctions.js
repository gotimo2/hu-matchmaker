function adminLogin(name, password){
    let fd = new FormData
    fd.append("name", name)
    fd.append("password", password)
    let ed = new URLSearchParams(fd.entries())
    fetch("/match/auth", {
        method:'POST',
        body:ed
    })
        .then(res => {if (res.ok){
            res.json()
                .then(data => {window.sessionStorage.setItem("JWT", data['JWT']);  document.querySelector("#adminLogin").open = false;})

        }
        else{
            window.alert("login failed!")
        }})
}

function editMatch(FormData){
    let ed = new URLSearchParams(FormData.entries())
    const jwttoken = window.sessionStorage.getItem('JWT');
    const bearer = 'Bearer ' + jwttoken;
    fetch("/match/admin", {
        method:'PUT',
        headers : {'Authorization': bearer},
        body:ed
    })
        .then(res => {
            if (res.ok){
                window.alert("match updated!")
                reloadData()
            }
            else{
                res.json()
                    .then(e => {
                        window.alert("error:" + e['result'])
                    })
            }
        })

}

function removePlayer(playername){
    const jwttoken = window.sessionStorage.getItem('JWT');
    const bearer = 'Bearer ' + jwttoken;
    let fd = new FormData
    fd.append("player", playername)
    let ed = new URLSearchParams(fd.entries())
    fetch("/match/admin/removeplayer", {
        method:'DELETE',
        headers : {'Authorization': bearer},
        body:ed
    })
        .then(res => {
            if (res.ok){
                window.alert("player removed!")
                reloadData()
            }
            else{
                res.json()
                    .then(e => {
                        window.alert("error:" + e['result'])
                    })
            }
        })
}

function fillTable(data) {
    let template = document.querySelector("#playertemplate")
    console.log(data)
    let m = new Array(data);
    for (let match of m) {
        console.log(match)
        for (let team of match) {
            let n = team['nummer']
            document.querySelector("#team" + n + "name").textContent = team['naam']
            document.querySelector("#t" + n + "nameinput").value = team['naam']
            document.querySelector("#team" + n).innerHTML = ""
            for (let player of team['spelers']) {
                console.log(player)
                let clone = template.content.cloneNode(true)
                let name = clone.querySelector("#name")
                name.textContent = player['naam']
                console.log('appended team')
                document.querySelector("#team" + n).appendChild(clone)
            }
        }
    }
}

function reloadData(){
    fetch("/match/" + matchid)
        .then(result => {
            if (result.ok) {
                result.json()
                    .then(data => {
                        nameHeading.textContent = data['matchname'];
                        orgnamep.textContent = data['organisator']['naam']
                        fillTable(data['teams'])
                    })
            } else {
                window.alert("Match not found!")
            }
        })
        .catch(err => {window.alert("error setting up match: " + err); console.log(err)})
}
