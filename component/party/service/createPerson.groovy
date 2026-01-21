// Validate that partyId, firstName, and lastName are provided
if (!partyId || !firstName || !lastName) {
    ec.message.addError("partyId, firstName, and lastName are required")
    return
}

// Verify that a Party record exists for the given partyId
def party = ec.entity.find("party.Party")
        .condition("partyId", partyId)
        .one()

if (!party) {
    ec.message.addError("Party does not exist for partyId: " + partyId)
    return
}

//Ensure the Person is created only if the Party exists
ec.service.sync()
        .name("create#party.Person")
        .parameters(context)
        .call()


responseMessage = "Person ${firstName} ${lastName} created successfully!"

