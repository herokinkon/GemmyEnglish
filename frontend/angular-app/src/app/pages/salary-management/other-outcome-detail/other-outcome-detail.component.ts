import { Component, EventEmitter, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ENTITY_ACTION } from 'src/app/shared/app-constant.service';
import { CommonEntityDialogInterface, EntityActionEvent } from '../../../shared/components/common-detail-dialog/common-entity-dialog-interface';
import { Staff } from '../../staff-management/staff-service/staff';
import { StaffService } from '../../staff-management/staff-service/staff.service';
import { OtherOutcome } from '../salary-service/salary';
import { SalaryService } from '../salary-service/salary.service';

@Component({
    selector: 'app-other-outcome-detail',
    templateUrl: './other-outcome-detail.component.html',
    styleUrls: ['./other-outcome-detail.component.css']
})
export class OtherOutcomeDetailComponent implements OnInit, CommonEntityDialogInterface<OtherOutcome> {

    otherOutcomeInfo: OtherOutcome;
    title: string;
    isNewOutcome: boolean;
    fields: any[];
    otherOutcome: OtherOutcome[];
    event: EventEmitter<EntityActionEvent<OtherOutcome>> = new EventEmitter();
    action = ENTITY_ACTION;
    staffData: Staff
    suggestionStaff: Staff[];

    constructor(private salaryService: SalaryService, private route: ActivatedRoute,
        private staffService: StaffService) {
        this.fields = [{ field: 'fullName', header: 'Full Name', cols: 2, required: 'true' },
        { field: 'birthday', header: 'Birthday', cols: 1 },
        { field: 'purpose', header: 'Purpose', cols: 2 },
        { field: 'usedDate', header: 'Date', cols: 1 },
        { field: 'cost', header: 'Cost', cols: 2 }];
    }

    setEntityDialogData(title: string, isNewEntity: boolean, entity: any): void {
        this.title = title;
        this.isNewOutcome = isNewEntity;
        this.otherOutcomeInfo = entity;
        // if (!this.isNewOutcome) {
        //     this.fields.unshift({ field: 'id', header: 'SalaryId', cols: 1 });
        // }
    }

    ngOnInit(): void {
        const id = this.route.snapshot.paramMap.get('id');
        if (id) {
            this.salaryService.getOtherOutcomeById(+id).subscribe((otherOutcome: OtherOutcome) => {
                this.otherOutcomeInfo = otherOutcome;
            });
            this.isNewOutcome = false;
        }
    }

    sendEvent(action: ENTITY_ACTION) {
        switch (action) {
            case ENTITY_ACTION.CREATE:
                this.salaryService.createOtherOutcome(this.otherOutcomeInfo).subscribe();
                break;
            case ENTITY_ACTION.EDIT:
                this.salaryService.updateOtherOutcome(this.otherOutcomeInfo).subscribe();
                break;
            case ENTITY_ACTION.DELETE:
                this.salaryService.deleteOtherOutcome(this.otherOutcomeInfo.id).subscribe();
                break;
        }

        this.event.emit({ action, entity: this.otherOutcomeInfo });
    }

    getEvent(): EventEmitter<EntityActionEvent<OtherOutcome>> {
        return this.event;
    }

    searchStaff(event: any) {
        this.staffService.searchStaffByName(event.query).subscribe(data => {
            if (data) {
                this.suggestionStaff = data;
            } else {
                this.suggestionStaff = [];
                this.staffData = null;
            }
        });
    }

    addStaffToTextBox(event: Staff) {
        this.otherOutcomeInfo.staffId = event.id;
        this.otherOutcomeInfo.fullName = event.fullName;
        this.otherOutcomeInfo.birthday = event.birthday;
    }
}
