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
  selectedStaff: Staff[] = [];
  staffSuggestion: Staff[];
  startD: Date;
  endD: Date;


  constructor(private staffService: StaffService, public dialog: MatDialog, private timelineServ: TimelineService) {
    this.options = {
      plugins: [dayGridPlugin, timeGridPlugin, interactionPlugin],
      header: {
        left: 'prev,next, today',
        center: 'title',
        right: 'dayGridMonth,timeGridWeek'
      },
      editable: true,
      height: 'parent',
      eventLimit: true,
      views: {
        dayGrid: {
          eventLimit: 3 // adjust to 6 only for timeGridWeek/timeGridDay
        }
      },
      minTime: '09:00:00',
      maxTime: '21:00:00',
      allDaySlot: false,
      selectable: true,
      fixedWeekCount: false,
      datesRender: (info: any) => {
        this.startD = info.view.activeStart;
        this.endD = info.view.activeEnd;
        this.getEventsForStaff();
      },
      dateClick: (event: any) => {
        const dialog = this.dialog.open(TimelineDetailComponent, { data: { date: event.date } });
      }
    };
  }

  searchStaff(event: any) {
    this.staffService.searchStaff(event.query, 'FT').subscribe(staffs => this.staffSuggestion = staffs);
  }

  addStaff(staff: Staff) {
    this.selectedStaff.push(staff);
  }

  removeStaff(staff: Staff) {
    const index = this.selectedStaff.indexOf(staff);
    this.selectedStaff.splice(index, 1);
  }

  getEventsForStaff() {
    const ids = this.selectedStaff.map(staff => staff.id);
    this.timelineServ.searchTimeline(ids, this.startD, this.endD).subscribe(res => this.events = res);
  }
}
