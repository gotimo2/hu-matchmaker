document.getElementById("jointeam1").addEventListener("click", ev => {
        ev.preventDefault()
        let fd = new FormData(document.getElementById("joinform"))
        fd.append("team", 1)
        fd.append("matchid", new URLSearchParams(window.location.search).get('matchid'))
        let ed = new URLSearchParams(fd.entries());
        fetch("/match/joinmatch", {
            method: 'POST',
            body: ed
        }).then(r => {
            if (r.ok) {
                location.reload()
            } else {
                r.json()
                    .then(res => {
                        window.alert("failed joining match: " + res['result'])})
            }

        }).catch(e => window.alert(e))
    }
)

document.getElementById("jointeam2").addEventListener("click", ev => {
        ev.preventDefault()
        let fd = new FormData(document.getElementById("joinform"))
        fd.append("team", 2)
        fd.append("matchid", new URLSearchParams(window.location.search).get('matchid'))
        let ed = new URLSearchParams(fd.entries());
        fetch("/match/joinmatch", {
            body: ed,
            method: 'POST'
        }).then(r => {
            if (r.ok) {
                location.reload()
            } else {
                r.json()
                    .then(res => {
                        window.alert("failed joining match: " + res['result'])})
            }

        }).catch(e => window.alert(e))
    }
)

