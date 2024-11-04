import { EventAssociationTypes } from './EventAssociationTypes';
import { Events } from './Events';
export interface EventAssociations {
    eventAssociationId: number;
    description: string;
    eventAssociationTypeCode: string;
    eventAssociationTypes?: EventAssociationTypes;
    events?: Events[];
}
