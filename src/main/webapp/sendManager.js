join_form = document.getElementById("join-form")
join_form.onsubmit = async (a) => {
    a.preventDefault()
    let id=document.getElementById("matchid").value;
    fetch("/match/" + id)
        .then(response => {
            if (response.ok){
                response.json()
                    .then(data => {
                        console.log(data)
                        let id = data['id']
                        window.sessionStorage.setItem("id", data['id'])
                        window.alert(id)
                    })

                window.location.replace(window.location.origin + "/match.html?matchid="+ id);
            }
            else {
                window.alert("No match by this code found!")
            }
        })
        .catch(error => window.alert("there was an error: " + error))

    //window.location.replace(window.location.origin + "/match.html?matchid="+ id);
}
