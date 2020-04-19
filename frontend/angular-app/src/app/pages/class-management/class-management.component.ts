import { Component, OnInit } from '@angular/core';
import { ENTITY_ACTION } from 'src/app/shared/app-constant.service';
import { CommonDialogService } from 'src/app/shared/components/common-detail-dialog/common-dialog.service';
import { EntityActionEvent } from 'src/app/shared/components/common-detail-dialog/common-entity-dialog-interface';
import { ClassDetailComponent } from './class-detail/class-detail.component';
import { ClassService } from './class-service/class.service';
import { Classes } from './class-service/classes-model';

@Component({
  selector: 'app-class-management',
  templateUrl: './class-management.component.html',
  styleUrls: ['./class-management.component.css']
})
export class ClassManagementComponent implements OnInit {

  classes: Classes[];
  selectedClass: any;
  cols: any[];

  constructor(private readonly dialog: CommonDialogService, private classService: ClassService) { }

  ngOnInit(): void {
    this.cols = [{ field: 'id', header: 'Id' },
    { field: 'className', header: 'Class Name' },
    { field: 'classCode', header: 'Class Code' },
    { field: 'startDate', header: 'Start Date' },
    { field: 'endDate', header: 'End Date' },
    { field: 'fee', header: 'Fee' }];

    this.classService.getClasses().subscribe(data => this.classes = data);
  }

  showDialogToAdd() {
    const result = this.dialog.openDialog('New Class', ClassDetailComponent, {});
    result.subscribe(evt => this.updateTable(evt, this.classes));
  }

  delete(clas: any) {
    const index = this.classes.findIndex(cl => clas.id === cl.id);
    this.classes.splice(index, 1);
    this.classService.deleteClass(clas);
  }

  updateTable(event: EntityActionEvent<Classes>, classList: Classes[]) {
    switch (event?.action) {
      case ENTITY_ACTION.CREATE:
        classList.push(event.entity);
        break;
      case ENTITY_ACTION.EDIT:
        const index = this.classes.findIndex(cl => event.entity.id === cl.id);
        if (index >= 0) {
          classList[index] = event.entity;
        }
        break;
      case ENTITY_ACTION.DELETE:
        classList.splice(this.classes.findIndex(cl => event.entity.id === cl.id), 1);
        break;
    }
  }

  onRowSelect(event: any) {
    const result = this.dialog.openDialog('Class Detail', ClassDetailComponent, { ...event.data });
    result.subscribe(evt => this.updateTable(evt, this.classes));
  }

}
