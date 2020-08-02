import { Component } from '@angular/core';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import interactionPlugin from '@fullcalendar/interaction';
import { Staff } from '../staff-management/staff-service/staff';
import { HttpClient } from '@angular/common/http';
import { StaffService } from '../staff-management/staff-service/staff.service';
import { CommonDialogService } from 'src/app/shared/components/common-detail-dialog/common-dialog.service';
import { TimelineDetailComponent } from './timeline-detail/timeline-detail.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-assistant-timeline',
  templateUrl: './assistant-timeline.component.html',
  styleUrls: ['./assistant-timeline.component.css']
})
export class AssistantTimelineComponent {
  events: any[];
  options: any;
  val: Staff[];
  results: Staff[];


  constructor(private http: HttpClient, private staffService: StaffService, public dialog: MatDialog) {
    this.http.get('/assets/scheduleevents.json').subscribe(res => this.events = res['data']);

    this.options = {
      plugins: [dayGridPlugin, timeGridPlugin, interactionPlugin],
      defaultDate: '2020-07-12',
      header: {
        left: 'prev,next, today',
        center: 'title',
        right: 'dayGridMonth,timeGridWeek,timeGridDay'
      },
      editable: true,
      height: 'parent',
      eventLimit: true,
      views: {
        dayGrid: {
          eventLimit: 3 // adjust to 6 only for timeGridWeek/timeGridDay
        }
      },
      selectable: true,
      dateClick: (event: any) => {
        console.log(event);
        const dialog = this.dialog.open(TimelineDetailComponent, { data: 'dialogData' });
      }
    };
  }

  search(event: any): void {
    this.staffService.searchStaff('Anh', 'FT').subscribe();
  }

}
