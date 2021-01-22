<#import "commons.ftlh" as c>
<@c.page "Create">
    <div class="field">
        <label for="code_snippet" hidden><#-- No label --></label>
        <textarea id="code_snippet" rows="10"
                  placeholder="// write your code here"></textarea>
    </div>
    <div class="field">
        <label for="time_restriction">Time restriction:</label>
        <input id="time_restriction" type="text" value="0"/>
    </div>
    <div class="field">
        <label for="views_restriction">Views restriction:</label>
        <input id="views_restriction" type="text" value="0"/>
    </div>
    <div id="error"></div>
    <button id="send_snippet" type="submit"
            onclick="send()">Submit</button>

    <script>
        function send() {
            const object = {
                "code": document.getElementById("code_snippet").value,
                "time": document.getElementById("time_restriction").value,
                "views": document.getElementById("views_restriction").value
            };

            const json = JSON.stringify(object);

            const xhr = new XMLHttpRequest();
            xhr.open("POST", "/api/code/new", false);
            xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");

            try {
                xhr.send(json);
                if (xhr.status !== 200) {
                    document.getElementById("error").innerText = xhr.response;
                } else {
                    const jsonResponse = JSON.parse(xhr.responseText);
                    const id = jsonResponse["id"];
                    window.location = "/code/" + id;
                }
            } catch (ignored) {
                alert("Something went wrong!");
            }
        }
    </script>
</@c.page>