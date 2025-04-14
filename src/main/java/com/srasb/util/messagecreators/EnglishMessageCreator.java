package com.srasb.util.messagecreators;

public class EnglishMessageCreator extends MessageCreator {
    @Override
    public String noCustomerWithIdMessage() {
        return "There is no customer with such id.";
    }

    @Override
    public String customerDeletedMessage() {
        return "The customer has been successfully deleted!";
    }

    @Override
    public String noSpaceWithIdMessage() {
        return "There is no space with such id.";
    }

    @Override
    public String validationFailedMessage() {
        return "Validation failed: ";
    }

    @Override
    public String spaceChangedMessage() {
        return "The space has been successfully changed!";
    }

    @Override
    public String spaceAddingErrorMessage() {
        return "Something went wrong when trying to add the changed space to the database.";
    }

    @Override
    public String spaceAlreadyExistsMessage() {
        return "The space with this name already exists!";
    }

    @Override
    public String spaceSavedMessage() {
        return "The space has been successfully saved!";
    }

    @Override
    public String spaceDeletedMessage() {
        return "The space has been successfully deleted!";
    }

    @Override
    public String spaceForReservationNotExists() {
        return "The space chosen for the reservation does not exist!";
    }

    @Override
    public String startTimeOverlapsMessage() {
        return "The start time of the reservation overlaps with the existing reservation!";
    }

    @Override
    public String endTimeOverlapsMessage() {
        return "The end time of the reservation overlaps with the existing reservation!";
    }

    @Override
    public String reservationSavedMessage() {
        return "The reservation has been successfully saved!";
    }

    @Override
    public String noReservationWithIdMessage() {
        return "There is no reservation with such id.";
    }

    @Override
    public String noRightsToDeleteReservationMessage() {
        return "You are not allowed to delete this reservation.";
    }

    @Override
    public String reservationDeletedMessage() {
        return "The reservation has been successfully deleted!";
    }

    @Override
    public String sessionStartedAdminMessage() {
        return "Session started for the administrator.";
    }

    @Override
    public String sessionStartedCustomerMessage() {
        return "Session started for the customer: ";
    }

    @Override
    public String noRegisteredUserWithNameMessage() {
        return "There is no registered user with such name. Please register.";
    }

    @Override
    public String customerExistsMessage() {
        return "The customer with this name already exists!";
    }

    @Override
    public String customerRegisteredMessage() {
        return "The customer has been registered: ";
    }
}
