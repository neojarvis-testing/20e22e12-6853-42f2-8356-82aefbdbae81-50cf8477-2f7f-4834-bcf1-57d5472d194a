#!/bin/bash
export CHROME_BIN=/usr/bin/chromium

if [ ! -d "/home/coder/project/workspace/angularapp" ]
then
    cp -r /home/coder/project/workspace/karma/angularapp /home/coder/project/workspace/;
fi

if [ -d "/home/coder/project/workspace/angularapp" ]
then
    echo "project folder present"
    cp /home/coder/project/workspace/karma/karma.conf.js /home/coder/project/workspace/angularapp/karma.conf.js;

    # checking for user.model.spec.ts
    if [ -e "/home/coder/project/workspace/angularapp/src/app/models/user.model.ts" ]
    then
        cp /home/coder/project/workspace/karma/user.model.spec.ts /home/coder/project/workspace/angularapp/src/app/models/user.model.spec.ts;
    else
        echo "frontend_User_model_should_create_an_instance FAILED";
    fi

    # checking for login.model.spec.ts
    if [ -e "/home/coder/project/workspace/angularapp/src/app/models/login.model.ts" ]
    then
        cp /home/coder/project/workspace/karma/login.model.spec.ts /home/coder/project/workspace/angularapp/src/app/models/login.model.spec.ts;
    else
        echo "frontend_Login_model_should_create_an_instance FAILED";
    fi

    # checking for vehicle-maintenance.model.spec.ts
    if [ -e "/home/coder/project/workspace/angularapp/src/app/models/vehicle-maintenance.model.ts" ]
    then
        cp /home/coder/project/workspace/karma/vehicle-maintenance.model.spec.ts /home/coder/project/workspace/angularapp/src/app/models/vehicle-maintenance.model.spec.ts;
    else
        echo "frontend_VehicleMaintenance_model_should_create_an_instance FAILED";
    fi

    # checking for appointment.model.spec.ts
    if [ -e "/home/coder/project/workspace/angularapp/src/app/models/appointment.model.ts" ]
    then
        cp /home/coder/project/workspace/karma/appointment.model.spec.ts /home/coder/project/workspace/angularapp/src/app/models/appointment.model.spec.ts;
    else
        echo "frontend_Appointment_model_should_create_an_instance FAILED";
    fi

    # checking for feedback.model.spec.ts
    if [ -e "/home/coder/project/workspace/angularapp/src/app/models/feedback.model.ts" ]
    then
        cp /home/coder/project/workspace/karma/feedback.model.spec.ts /home/coder/project/workspace/angularapp/src/app/models/feedback.model.spec.ts;
    else
        echo "frontend_Feedback_model_should_create_an_instance FAILED";
    fi

    # checking for auth.service.spec.ts
    if [ -e "/home/coder/project/workspace/angularapp/src/app/services/auth.service.ts" ]
    then
        cp /home/coder/project/workspace/karma/auth.service.spec.ts /home/coder/project/workspace/angularapp/src/app/services/auth.service.spec.ts;
    else
        echo "frontend_should_create_auth_service FAILED";
    fi

    # checking for vehicle.service.spec.ts
    if [ -e "/home/coder/project/workspace/angularapp/src/app/services/vehicle.service.ts" ]
    then
        cp /home/coder/project/workspace/karma/vehicle.service.spec.ts /home/coder/project/workspace/angularapp/src/app/services/vehicle.service.spec.ts;
    else
        echo "frontend_should_create_vehicle_service FAILED";
    fi

    # checking for appointment.service.spec.ts
    if [ -e "/home/coder/project/workspace/angularapp/src/app/services/appointment.service.ts" ]
    then
        cp /home/coder/project/workspace/karma/appointment.service.spec.ts /home/coder/project/workspace/angularapp/src/app/services/appointment.service.spec.ts;
    else
        echo "frontend_should_create_appointment_service FAILED";
    fi

    # checking for feedback.service.spec.ts
    if [ -e "/home/coder/project/workspace/angularapp/src/app/services/feedback.service.ts" ]
    then
        cp /home/coder/project/workspace/karma/feedback.service.spec.ts /home/coder/project/workspace/angularapp/src/app/services/feedback.service.spec.ts;
    else
        echo "frontend_should_create_feedback_service FAILED";
    fi

    # checking for userdetails.service.spec.ts
    if [ -e "/home/coder/project/workspace/angularapp/src/app/services/userdetails.service.ts" ]
    then
        cp /home/coder/project/workspace/karma/userdetails.service.spec.ts /home/coder/project/workspace/angularapp/src/app/services/userdetails.service.spec.ts;
    else
        echo "frontend_should_create_userdetails_service FAILED";
    fi

    
    if [ -d "/home/coder/project/workspace/angularapp/node_modules" ]; 
    then
        cd /home/coder/project/workspace/angularapp/
        npm test;
    else
        cd /home/coder/project/workspace/angularapp/
        yes | npm install
        npm test
    fi 
else   
    echo "frontend_User_model_should_create_an_instance FAILED";
    echo "frontend_Login_model_should_create_an_instance FAILED";
    echo "frontend_VehicleMaintenance_model_should_create_an_instance FAILED";
    echo "frontend_Appointment_model_should_create_an_instance FAILED";
    echo "frontend_Feedback_model_should_create_an_instance FAILED";
    echo "frontend_should_create_auth_service FAILED";
    echo "frontend_should_create_vehicle_service FAILED";
    echo "frontend_should_create_appointment_service FAILED";
    echo "frontend_should_create_feedback_service FAILED";
    echo "frontend_should_create_userdetails_service FAILED";
fi