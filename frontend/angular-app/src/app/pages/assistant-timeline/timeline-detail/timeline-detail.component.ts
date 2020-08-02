import { Component, Inject } from '@angular/core';
import { TimelineEvent } from '../timeline-service/timeline-event';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-timeline-detail',
  templateUrl: './timeline-detail.component.html',
  styleUrls: ['./timeline-detail.component.css']
})
export class TimelineDetailComponent {
  constructor(@Inject(MAT_DIALOG_DATA) public data: any) { }

  delete() {

  }

  save() {

  }
}
