import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2';
import { Feedback } from 'src/app/models/feedback.model';
import { FeedbackService } from 'src/app/services/feedback.service';

@Component({
  selector: 'app-userviewfeedback',
  templateUrl: './userviewfeedback.component.html',
  styleUrls: ['./userviewfeedback.component.css']
})
export class UserviewfeedbackComponent implements OnInit {
  userId: any;
  feedbacks: Feedback[] = [];

  constructor(private feedbackService: FeedbackService) { }

  ngOnInit(): void {
    this.userId = localStorage.getItem('userId');
    this.getAllFeedbackByUserId();
  }

  getAllFeedbackByUserId(): void {
    this.feedbackService.getFeedbackByUserId(this.userId).subscribe((data) => {
      this.feedbacks = data;
    });
  }

  deleteFeedback(id: number): void {
    Swal.fire({
      title: 'Are you sure?',
      text: 'You won’t be able to revert this!',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Yes, delete it!',
      cancelButtonText: 'No, keep it'
    }).then((result) => {
      if (result.isConfirmed) {
        this.feedbackService.deleteFeedbackById(id).subscribe(() => {
          this.getAllFeedbackByUserId();
          Swal.fire('Deleted!', 'Feedback has been deleted.', 'success');
        });
      }
    });
  }
}
