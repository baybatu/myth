/*
 * Copyright 2012, Ufuk Uzun (http://www.ufukuzun.com/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

Myth = {

    ajax: function (source, config) {

        $.ajax({
            type: "POST",
            url: config.url,
            contentType: "application/json",
            dataType: "text",
            data: JSON.stringify({
                "model": this.inputsToJSON(config.process),
                "update": config.update
            }),
            success: function (response) {
                var responseUpdates = JSON.parse(response).updates;
                for (var eachResponseUpdate in responseUpdates) {
                    for (var eachRequestUpdate in config.update) {
                        for (var eachId in config.update[eachRequestUpdate]) {
                            var requestUpdateId = (config.update[eachRequestUpdate])[eachId];
                            var responseUpdateId = responseUpdates[eachResponseUpdate].id;
                            var responseUpdateContent = responseUpdates[eachResponseUpdate].content;
                            if (requestUpdateId == responseUpdateId) {
                                if (responseUpdateContent == "") {
                                    $('#' + requestUpdateId).remove();
                                } else {
                                    $('#' + requestUpdateId).replaceWith($(responseUpdateContent));
                                }
                                break;
                            }
                        }
                    }
                }
            }
        });
    },

    inputsToJSON: function (process) {
        var json = {};
        for (var id in process) {
            $.each($('#' + process[id]).serializeArray(), function () {
                if (json[this.name] !== undefined) {
                    if (!json[this.name].push) {
                        json[this.name] = [json[this.name] ];
                    }
                    json[this.name].push(this.value || '');
                } else {
                    json[this.name] = this.value || '';
                }
            });
        }
        return json;
    }

};