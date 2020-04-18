import { Component, Inject, ViewChild, ViewContainerRef, ComponentFactoryResolver, AfterViewInit, ChangeDetectorRef } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ComponentType } from '@angular/cdk/portal';
import { CommonEntityDialogInterface, EntityActionEvent } from './common-entity-dialog-interface';

@Component({
  selector: 'app-common-detail-dialog',
  templateUrl: './common-detail-dialog.component.html',
  styleUrls: ['./common-detail-dialog.component.css']
})
export class CommonDetailDialogComponent implements AfterViewInit {
  @ViewChild('detailView', { read: ViewContainerRef }) detailView: ViewContainerRef;
  constructor(public dialogRef: MatDialogRef<CommonDetailDialogComponent, EntityActionEvent<any>>,
              @Inject(MAT_DIALOG_DATA) private readonly data: any,
              private readonly resolver: ComponentFactoryResolver, private readonly cd: ChangeDetectorRef) {
  }

  generateDialogContent(comp: ComponentType<CommonEntityDialogInterface<any>>) {
    const compFactory = this.resolver.resolveComponentFactory(comp);
    const component = this.detailView.createComponent<CommonEntityDialogInterface<any>>(compFactory);
    component.instance.setEntityDialogData(this.data.title, Object.keys(this.data.entity).length === 0, this.data.entity);
    component.instance.getEvent().subscribe(event => this.dialogRef.close(event));
  }

  ngAfterViewInit(): void {
    this.generateDialogContent(this.data.component);
    this.cd.detectChanges();
  }

}
