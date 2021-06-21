function leave(name, password) {
    let fd = new FormData;
    fd.append('name', name)
    fd.append('pass', password)
    fd.append('matchid', matchid)
    let ed = new URLSearchParams(fd.entries())
    fetch("/match/leave", {
        method: 'DELETE',
        body: ed
    })
        .then(res => {
                if (res.ok) {
                    window.alert("match left!")
                    location.reload()
                } else {
                    window.alert("error leaving match!")
                }
            }
        )
}

