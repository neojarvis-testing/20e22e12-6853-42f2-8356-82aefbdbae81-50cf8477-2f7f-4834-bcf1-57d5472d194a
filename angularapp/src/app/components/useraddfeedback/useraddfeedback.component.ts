import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Feedback } from 'src/app/models/feedback.model';
import { User } from 'src/app/models/user.model';
import { AuthService } from 'src/app/services/auth.service';
import { FeedbackService } from 'src/app/services/feedback.service';

@Component({
  selector: 'app-useraddfeedback',
  templateUrl: './useraddfeedback.component.html',
  styleUrls: ['./useraddfeedback.component.css']
})
export class UseraddfeedbackComponent implements OnInit {
  
  userId: number = Number(localStorage.getItem('userId')) || 0; // Ensure userId is a number
  feedbackForm: FormGroup;
  successMessage: string = '';
  showModal: boolean = false;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private feedbackService: FeedbackService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.feedbackForm = this.fb.group({
      message: ['', [Validators.required, Validators.minLength(5)]],
      rating: ['', [Validators.required, Validators.min(1), Validators.max(5)]]
    });
  }

  ngOnInit(): void {
    if (!this.userId) {
      console.error('User ID missing, fetching from AuthService...');
      this.getUserDetails(this.userId);
    }
  }

  getUserDetails(userId: number): void {
    this.authService.getUserId(userId).subscribe(
      (user) => {
        if (user && user.userId) {
          this.userId = user.userId;
          localStorage.setItem('userId', String(user.userId)); // Store for persistence
          console.log('User ID:', this.userId);
        }
      },
      (error) => {
        console.error('Error fetching user details:', error);
      }
    );
  }

  submitFeedback(): void {
    if (!this.userId) {
      console.error('User ID is required for feedback submission.');
      return;
    }

    if (this.feedbackForm.valid) {
      const newFeedback: Feedback = {
        userId: this.userId, // Use userId directly
        message: this.feedbackForm.value.message,
        rating: this.feedbackForm.value.rating
      };

      console.log('Submitting feedback:', newFeedback);

      this.feedbackService.createFeedback(newFeedback).subscribe(() => {
        this.successMessage = 'Feedback submitted successfully!';
        setTimeout(() => this.successMessage = '', 5000);
        this.feedbackForm.reset();
        this.showModal = true;
      },
      (error) => {
        console.error('Error submitting feedback:', error);
      });
    }
  }

  closeModal(): void {
    this.showModal = false;
    this.router.navigate(['/user-feedbacks']);
  }
}
