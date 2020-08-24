import { Component } from '@angular/core';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import interactionPlugin from '@fullcalendar/interaction';
import { Staff } from '../staff-management/staff-service/staff';
import { StaffService } from '../staff-management/staff-service/staff.service';
import { TimelineDetailComponent } from './timeline-detail/timeline-detail.component';
import { MatDialog } from '@angular/material/dialog';
import { TimelineService } from './timeline-service/timeline.service';

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


  constructor(private staffService: StaffService, public dialog: MatDialog, private timelineServ: TimelineService) {
    this.timelineServ.getTimelines().subscribe(res => this.events = res);

    this.options = {
      plugins: [dayGridPlugin, timeGridPlugin, interactionPlugin],
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
        const dialog = this.dialog.open(TimelineDetailComponent, { data: { date: event.date } });
      }
    };
  }

  search(event: any): void {
    this.staffService.searchStaff('Anh', 'FT').subscribe();
  }

}
