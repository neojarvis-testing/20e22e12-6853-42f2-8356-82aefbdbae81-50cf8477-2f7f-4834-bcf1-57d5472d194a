import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Feedback } from 'src/app/models/feedback.model';
import { FeedbackService } from 'src/app/services/feedback.service';
 
@Component({
  selector: 'app-useraddfeedback',
  templateUrl: './useraddfeedback.component.html',
  styleUrls: ['./useraddfeedback.component.css']
})
export class UseraddfeedbackComponent implements OnInit {
 
//   newFeedback:Feedback={
//     userId:localStorage.getItem('userId'),
//     message:'',
//     rating:0
//   }
//   constructor(private feedbackService:FeedbackService) { }
 
//   ngOnInit(): void {
//   }
 
//   addFeedback():void{
//     this.feedbackService.addFeedback(this.newFeedback).subscribe((data)=>{
//       alert('feedback added successfully');
//     })
//   }
// }

feedbackForm: FormGroup; // Form group for managing user input
successMessage: string = ''; // Message to display upon successful submission

constructor(private fb: FormBuilder, private feedbackService: FeedbackService) {
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
        alert(this.successMessage);
      },
      (error) => {
        console.error('Error submitting feedback:', error);
      }
    );
  }
}
}
 