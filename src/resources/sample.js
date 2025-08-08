async function processFeedConvertForMultipleCompetitors(clientId, projectId, catIds, competitorMappings) {
    const delay = (ms) => new Promise(resolve => setTimeout(resolve, ms));

    for (const catId of catIds) {
        await getCategoryMappingDetails(catId);
        await delay(500);

        if (catMapping && attMapping) {
            var confirmation = confirm("Do You want To Continue for Category ID: " + catId + " with " + competitorMappings.length + " competitor(s)?");
            if (!confirmation) {
                location.reload();
                return;
            }

            for (let i = 0; i < competitorMappings.length; i++) {
                const mapping = competitorMappings[i];
                console.log(`Processing Category ${catId} with Competitor ${mapping.competitorId} (${i + 1}/${competitorMappings.length})`);

                if (getCompetitorCategoryId) {
                    getclientCategoryName(clientId, projectId, getClientCategoryId2);
                    getCompCategoryName(mapping.competitorId, mapping.competitor_projectId, getCompetitorCategoryId);

                    if (getclientCategoryName && getCompCatName) {
                        getClientFileForFeedConvert(catId);
                        getCompetitorFileForFeedConverts(catId, mapping.competitorId, mapping.competitor_projectId);
                        getclientCategoryName(clientId, projectId, catId);
                        getCompCategoryName(mapping.competitorId, mapping.competitor_projectId, getCompetitorCategoryId);

                        console.log("Sending to controller:", catId, mapping.competitorId, mapping.competitor_projectId, mapping.batchId);
                        sendFileDetailsToController(catId, mapping.competitorId, mapping.competitor_projectId, mapping.batchId);
                    } else {
                        alert(`Category Not Mapped for Category ID: ${catId} with Competitor ID: ${mapping.competitorId}`);
                    }
                } else {
                    alert(`You Stopped Feed Conversion for Category ID: ${catId} with Competitor ID: ${mapping.competitorId}`);
                }

                if (i < competitorMappings.length - 1) {
                    await delay(800);
                }
            }
        }

        await delay(1000);
    }
}

function sendFileDetailsToController(categoryId, dynamicCompetitorId = null, dynamicCompetitorProjectId = null, dynamicBatchId = null) {
    $("#spinner").css("display", "block");

    var clientId = $("#client").val();
    var projectId = $("#project").val();
    
    // Use dynamic values if provided, otherwise fall back to static dropdowns
    var competitorId = dynamicCompetitorId || $("#competitor").val();
    var competitorProjectId = dynamicCompetitorProjectId || $("#competitor_project").val();
    var batchId = dynamicBatchId || $("#batch").val();
    
    console.log("=== sendFileDetailsToController Debug ===");
    console.log("dynamicCompetitorId:", dynamicCompetitorId);
    console.log("dynamicCompetitorProjectId:", dynamicCompetitorProjectId);
    console.log("dynamicBatchId:", dynamicBatchId);
    console.log("Final competitorId:", competitorId);
    console.log("Final competitorProjectId:", competitorProjectId);
    console.log("Final batchId:", batchId);
    
    // Get batch name - handle case where fetchBatch might not be available
    var batchName = "";
    if (typeof fetchBatch !== 'undefined' && fetchBatch && fetchBatch.length > 0) {
        for (var i = 0; i < fetchBatch.length; i++) {
            var currentData = fetchBatch[i]; 
            if (currentData.batchId === batchId) {
                batchName = currentData.batchName;
                break;
            }
        }
    }
    
    // If no batch name found and we have a batchId, use it as fallback
    if (!batchName && batchId) {
        batchName = "Batch_" + batchId;
    }
    
    console.log("categoryName: " + getClientCatName);
    console.log("batchName: " + batchName);

    // Build your payload with dynamic competitor values
    var fdata1 = {
        "clientFile": getClientFile,
        "clientDataRow": getClientDataRow,
        "clientAttributeRow": getClientAttributeRow,
        "clientSheetno": getClientSheetno,
        "clientCategoryName": getClientCatName,
        "compFile": getCompFile,
        "compDataRow": getCompDataRow,
        "compAttributeRow": getCompAttributeRow,
        "compSheetNo": getCompSheetNo,
        "compCategoryName": getCompCatName,
        "clientAttrList": getclientAttrName,
        "compAttrList": getcompAttrName,
        "clientDataKey": currentAttribute,
        "clientDataValue": currentAttributeCValue,
        "clientId": clientId,
        "competitorId": competitorId,          // Now uses dynamic value
        "projectId": projectId,
        "compProjectId": competitorProjectId,  // Now uses dynamic value
        "batchId": batchId,                    // Now uses dynamic value
        "categoryId": categoryId,
        "stageId": "STG00001",
        "categoryName": getClientCatName,
        "batchName": batchName,
        "LOVMappingStatus": LOVMappingEnabled
    };

    console.log("Final payload being sent:", JSON.stringify(fdata1, null, 2));

    var apiUrl = portCatalogue + feedConvertData + "/" + LOVMappingEnabled + "/" + encodeURIComponent(getClientCatName) + "/" + encodeURIComponent(batchName);

    $.ajax({
        url: apiUrl,
        type: "POST",
        data: JSON.stringify(fdata1),
        dataType: 'text',
        contentType: 'application/json; charset=utf-8',
        async: true,
        success: function(data) {
            $("#spinner").css("display", "none");
            if (typeof data === 'string') {
                window.location.href = "/download/" + data;
            } else {
                console.error("Unexpected response format:", data);
            }
        },
        error: function(data) {
            $("#spinner").css("display", "none");
            console.log("Error Response: " + JSON.stringify(data));
            console.log("Payload Sent: " + JSON.stringify(fdata1));
            alert("Error in sendFileDetailsToController");
        }
    });
}