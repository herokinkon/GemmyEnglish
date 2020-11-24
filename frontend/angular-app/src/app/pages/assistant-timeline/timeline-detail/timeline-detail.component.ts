import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
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
  isRepeatEvent: boolean;
  eventId: number;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private staffService: StaffService, private timelineSer: TimelineService,
    // tslint:disable-next-line:align
    public dialogRef: MatDialogRef<any>) {
    if (data.title) {
      this.timelineSer.getTimeline(data.id).subscribe(dat => {
        this.eventId = dat.id;
        this.title = dat.title;
        this.description = dat.description;
        this.selectedStaff = dat.staff;
        this.staffSuggestion = [this.selectedStaff];

        if (dat.daysOfWeek) {
          this.isRepeatEvent = true;
          this.daysOfWeek = Array.from(dat.daysOfWeek);
          this.date = dat.startRecur;
          this.repeatDate = dat.endRecur;
          this.startTime = dat.startTime;
          this.endTime = dat.endTime;
        } else {
          this.date = new Date(dat.start);
          this.startTime = `${this.date.getHours()}:${this.date.getMinutes()}`;
          this.endTime = `${new Date(dat.end).getHours()}:${new Date(dat.end).getMinutes()}`;
        }
      });
    } else {
      this.date = data.date;
    }
  }

  delete() {
    this.timelineSer.deleteTimeline(this.eventId, this.title).subscribe(_ => this.dialogRef.close(true));
  }

  save() {
    let nData = {};
    if (this.isRepeatEvent) {
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
    this.timelineSer.createTimeline(nData).subscribe(_ => this.dialogRef.close(true));
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
