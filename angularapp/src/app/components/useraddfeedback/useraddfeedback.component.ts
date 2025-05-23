import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Feedback } from 'src/app/models/feedback.model';
import { FeedbackService } from 'src/app/services/feedback.service';
 
@Component({
  selector: 'app-useraddfeedback',
  templateUrl: './useraddfeedback.component.html',
  styleUrls: ['./useraddfeedback.component.css']
})
export class UseraddfeedbackComponent implements OnInit {
 
  feedbackForm: FormGroup; // Form group for managing user input
  successMessage: string = ''; // Message to display upon successful submission
 
  constructor(private fb: FormBuilder, private feedbackService: FeedbackService, private router:Router) {
    // Initializing the form with validation rules
    this.feedbackForm = this.fb.group({
      message: ['', [Validators.required, Validators.minLength(5)]], // Ensures message is required and has at least 5 characters
      rating: ['', [Validators.required, Validators.min(1), Validators.max(5)]] // Ensures rating is between 1 and 5
    });
  }
 
  ngOnInit(): void {
  }
 
  // Method to submit feedback
  addFeedback(): void {
    const userId = Number(localStorage.getItem('userId')); // Fetch user ID from local storage
   
    if (!userId) {
      console.error('User ID is required for feedback submission.');
      return;
    }
 
    if (this.feedbackForm.valid) {
      const newFeedback: Feedback = {
        userId: userId, // Ensure correct user ID
        message: this.feedbackForm.value.message,
        rating: this.feedbackForm.value.rating
      };
 
      console.log('Submitting feedback:', newFeedback);
 
      this.feedbackService.addFeedback(newFeedback).subscribe(
        () => {
          this.successMessage = 'Feedback added successfully!';
          this.feedbackForm.reset(); // Reset form after successful submission
          setTimeout(() => {
            this.router.navigate(['/view-feedback']);
          }, 2000); // Delay for 2 seconds to show success message
         
        },
        (error) => {
          console.error('Error submitting feedback:', error);
        }
      );
    }
  }
}