import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Staff } from '../../staff-management/staff-service/staff';
import { StaffService } from '../../staff-management/staff-service/staff.service';
import { TimelineService } from '../timeline-service/timeline.service';

@Component({
  selector: 'app-timeline-detail',
  templateUrl: './timeline-detail.component.html',
  styleUrls: ['./timeline-detail.component.css']
})
export class TimelineDetailComponent {
  title: string;
  description: string;
  staffSuggestion: Staff[];
  selectedStaff: Staff;
  date: Date;
  startTime: string;
  endTime: string;
  repeatDate: Date;
  daysOfWeek: number[];

  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private staffService: StaffService, private timelineSer: TimelineService) {
    this.date = data.date;
    this.repeatDate = new Date();
  }

  delete() {

  }

  save(isRecur: boolean) {
    let nData = {};
    if (isRecur) {
      nData = {
        title: this.title, startTime: this.startTime, endTime: this.endTime, description: this.description,
        staff: this.selectedStaff, startRecur: this.date, endRecur: this.repeatDate,
        daysOfWeek: this.daysOfWeek
      };
    } else {
      const start = this.appendTimeToDate(this.date, this.startTime);
      const end = this.appendTimeToDate(this.date, this.endTime);
      nData = { title: this.title, start, end, staff: this.selectedStaff, description: this.description };
    }
    this.timelineSer.createTimeline(nData).subscribe();
  }

  searchStaff(event: any) {
    this.staffService.searchStaff(event.query, 'FT').subscribe(staffs => this.staffSuggestion = staffs);
  }

  appendTimeToDate(date: Date, time: string): Date {
    const nDate = new Date(date);
    const hours = parseInt(time.split(':')[0], 10);
    const mins = parseInt(time.split(':')[0], 10);
    nDate.setHours(hours, mins);
    return nDate;
  }
}
