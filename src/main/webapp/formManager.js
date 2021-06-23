org_form = document.getElementById("org-form")
console.log(org_form)
org_form.onsubmit = async (a) => {
    a.preventDefault();
    let fd = new FormData(org_form);
    let ed = new URLSearchParams(fd.entries())
    fetch("/match/newmatch", {
        method: 'POST',
        body: ed,
    })
        .then(response => {
            if (response.ok) {
                response.json()
                    .then(data => {
                        window.sessionStorage.setItem("id", data['id'])
                        window.location.replace(window.location.origin + "/match.html?matchid=" + data['id'])
                    })
            } else {
                response.json()
                    .then(data => {
                        window.alert(data['result'])
                        console.log(data)})
            }
        })
        .catch((error) => {
            window.alert("there was an error: " + error);
        })
};
