import { ENTITY_ACTION } from '../../app-constant.service';
import { EventEmitter } from '@angular/core';

export interface CommonEntityDialogInterface<E> {
  setEntityDialogData(title: string, isNewEntity: boolean, entity: E): void;
  getEvent(): EventEmitter<EntityActionEvent<E>>;
}

export interface EntityActionEvent<E> {
  action: ENTITY_ACTION;
  entity: E;
}
