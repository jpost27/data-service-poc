import { EventAssociations } from './EventAssociations';
import { Events } from './Events';
export interface EventsEventAssociations {
    eventId: number;
    eventAssociationId: number;
    events?: Events;
    eventAssociations?: EventAssociations;
}
