import { Component, OnInit } from '@angular/core';

import { Feedback } from 'src/app/models/feedback.model';

import { User } from 'src/app/models/user.model';

import { FeedbackService } from 'src/app/services/feedback.service';
 

@Component({

  selector: 'app-adminviewfeedback',

  templateUrl: './adminviewfeedback.component.html',

  styleUrls: ['./adminviewfeedback.component.css']

})

export class AdminviewfeedbackComponent implements OnInit {



  feedbacks: Feedback[] = []; // List of feedbacks

  userDetails: any[] = []; // Store feedbacks with user details

  userDetail: User | null = null; // Selected user profile

  userId: number = Number(localStorage.getItem('userId')) || 0; // Ensure userId is a number

  constructor(private readonly feedbackService: FeedbackService) {}


  ngOnInit(): void {

    this.fetchFeedbacks();

    this.fetchLoggedInUser();

  }

  /**
   * Fetch all feedbacks and associate them with usernames.
   */

  fetchFeedbacks(): void {

    this.feedbackService.getAllFeedbacks().subscribe((data) => {

      console.log('Feedback Data:', data); // Debugging

      this.feedbacks = data;

      this.mapUsernames();

    });

  }

  /**
   * Get details of the logged-in user.
   */

  fetchLoggedInUser(): void {

    this.feedbackService.getUserDetailById(this.userId).subscribe((data) => {

      console.log('Logged-in user:', data);

    });

  }

  /**
   * Maps usernames to feedbacks efficiently.
   */

  mapUsernames(): void {


    this.userDetails = this.feedbacks.map(feedback => ({

      ...feedback,

      username: '',

    }));

    this.userDetails.forEach(user => {

      this.feedbackService.getUserDetailById(user.userId).subscribe((data) => {

        user.username = data.username;

        console.log('Updated userDetails:', this.userDetails); // Debugging

      });

    });

  }

  /**
   * Show user profile in a modal.
   * @param id - The user's ID
   */

  showProfile(id: number): void {

    this.feedbackService.getUserDetailById(id).subscribe((data) => {

      this.userDetail = data; // Show profile in modal

    });

  }

  /**
   * Close profile modal.
   */

  closeProfile(): void {

    this.userDetail = null; // Hide modal

  }

}