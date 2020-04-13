import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { CommonDetailDialogComponent } from './common-detail-dialog.component';
import { CommonEntityDialogInterface, EntityActionEvent } from './common-entity-dialog-interface';
import { ComponentType } from '@angular/cdk/portal';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CommonDialogService {

  constructor(private readonly dialog: MatDialog) { }

  openDialog(title: string, component: ComponentType<CommonEntityDialogInterface<any>>, entity: any): Observable<EntityActionEvent<any>> {
    const data = { title, entity, component };
    const dialogRef = this.dialog.open<any, any, EntityActionEvent<any>>(CommonDetailDialogComponent, { data });
    return dialogRef.afterClosed();
  }
}
